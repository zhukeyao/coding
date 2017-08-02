package org.apache.flink.quickstart;

import com.dataartisans.flinktraining.exercises.datastream_java.datatypes.TaxiRide;
import com.dataartisans.flinktraining.exercises.datastream_java.sources.TaxiRideSource;
import com.dataartisans.flinktraining.exercises.datastream_java.utils.GeoUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;


/**
 * Created by keyao on 4/10/17.
 */
public class NYCTaxRide {
    public static void main(String[] args) throws Exception {
		// set up the streaming execution environment

		//ParameterTool params = ParameterTool.fromArgs(args);
		// "/home/keyao/data/nycTaxiRides.gz"
		//final String input = params.getRequired("input");
		final String input =  "/home/keyao/data/nycTaxiRides.gz";

		final StreamExecutionEnvironment env =
				StreamExecutionEnvironment.getExecutionEnvironment();
		// configure event-time processing
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		// get the taxi ride data stream
		int maxDelay = 60;
		int servingSpeed = 600;
		DataStream<TaxiRide> rides = env.addSource(
				new TaxiRideSource(input, maxDelay, servingSpeed));

		/*
		DataStream<TaxiRide> output = rides.filter(new FilterFunction<TaxiRide>() {
			@Override
			public boolean filter(TaxiRide taxiRide) throws Exception {
				if (taxiRide.passengerCnt > 2)
					return false;
				return true;
			}
		});*/

		DataStream<Tuple5<Float, Float, Long, Boolean, Integer>> output =
				rides.map(new MapFunction<TaxiRide, Tuple2<Integer, Boolean>>() {
                            @Override
                            public Tuple2<Integer, Boolean> map(TaxiRide taxiRide) throws Exception {
                            	if (taxiRide.isStart) {
                            		Integer cell = GeoUtils.mapToGridCell(taxiRide.startLat, taxiRide.startLon);
                            		return new Tuple2<>(cell, true);
								}
								else {
									Integer cell = GeoUtils.mapToGridCell(taxiRide.endLat, taxiRide.endLon);
									return new Tuple2<>(cell, false);
								}
                            }
                        })
                     .keyBy(0, 1)
					 .timeWindow(Time.minutes(15), Time.minutes(5))
					 .apply(new WindowFunction<Tuple2<Integer, Boolean>, Tuple4<Integer,Boolean,Long,Integer>, Tuple, TimeWindow>() {
								    @Override
								    public void apply(Tuple key, TimeWindow window, Iterable<Tuple2<Integer, Boolean>> rides, Collector<Tuple4<Integer,Boolean, Long, Integer>> collector) throws Exception {
								    	int count = 0;
								    	for (Tuple2 ride : rides) {
											count++;
										}
										Long ts = window.getEnd();
								    	Tuple4<Integer, Boolean, Long, Integer> output = new Tuple4<Integer, Boolean, Long, Integer>((Integer)key.getField(0), (Boolean)key.getField(1), ts, count);
										collector.collect(output);
									}

						       }
						      )
					 .map(new MapFunction<Tuple4<Integer, Boolean, Long, Integer>, Tuple5<Float, Float, Long, Boolean, Integer>>() {
							@Override
							public Tuple5<Float, Float, Long, Boolean, Integer> map(Tuple4<Integer, Boolean, Long, Integer> input) throws Exception {
								float lat = GeoUtils.getGridCellCenterLat((Integer)input.getField(0));
							    float log = GeoUtils.getGridCellCenterLon((Integer)input.getField(0));
							    Tuple5<Float, Float, Long, Boolean, Integer> output = new Tuple5<Float, Float, Long, Boolean, Integer>(lat, log, (Long)input.getField(2), (Boolean)input.getField(1), (Integer)input.getField(3));
							    return output;
							}
						});





		// execute program
		output.print();
		env.execute("Flink Streaming Java API Skeleton");
	}
}
