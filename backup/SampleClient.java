package com.getjar.sample.service;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.getjar.common.id.ObjectId;
import com.getjar.common.id.ObjectIdToStringTranslator;
import com.getjar.common.id.StringToObjectIdTranslator;
import com.getjar.common.tales.StringToBigDecimalTranslator;
import com.getjar.common.tales.TalesResponse;
import com.getjar.common.tales.TalesServiceProxyBase;
import com.getjar.common.tales.TalesServiceProxyConfiguration;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.tales.parts.translators.Translator;
import com.tales.serialization.json.JsonTranslationFacility;
import com.tales.serialization.json.translators.JsonElementToStringToChainTranslator;
import com.tales.serialization.json.translators.NumberToJsonPrimitiveTranslator;

/**
   * @author Keyao
   *
 */
public class SampleClient extends TalesServiceProxyBase {


    private static JsonTranslationFacility jsonTranslationFacility;
    private final Logger logger = LoggerFactory.getLogger(SampleClient.class);
    private static final Map<Class<?>, Translator> translatorCache= new ConcurrentHashMap<Class<?>, Translator>();
        
    /**
     * Constructor.
     *
     * @param theUrl The url, including port number, of the user service.
     * @param theJsonTranslationFacility
     */
    public SampleClient(String theUrl, JsonTranslationFacility theJsonTranslationFacility) {
       super( theUrl, "20140525", theJsonTranslationFacility);
       jsonTranslationFacility = theJsonTranslationFacility;
           registerTranslators();
    }

    /**
      * @param config
     */
    public SampleClient(TalesServiceProxyConfiguration config) {
        super(config, "20140525");
        jsonTranslationFacility = config.getTranslationFacility();
           registerTranslators();
    }
        
    private void registerTranslators() {
        //Register the translators for Void
        Translator voidTranslator = new Translator() {
            @Override
            public Object translate(Object arg0) {
                return null;
            }
        };

        jsonTranslationFacility.registerStringTranslators(
                Void.class,
                voidTranslator,
                voidTranslator );
                
    }

     public TalesResponse<ExternalMyObject> getReport() {
            logger.info("Enter : getReport");
            
            Translator fromTranslator = getTranslator(ExternalMyObject.class);

            List<CallParam> params = new ArrayList<CallParam>();
            params.add(new CallParam("value", String.class, null, "test", false));
         
                    
            /*params.add(new CallParam("report_name", String.class, null, report, false));
            params.add(new CallParam("report_key", String.class, null, reportKey, false));
            params.add(new CallParam("country_code", String.class, null, countryCode, false));
            params.add(new CallParam("start_date", DateTime.class, null, startDate, false));
            params.add(new CallParam("end_date", DateTime.class, null, endDate, false));
            params.add(new CallParam("scope", ReportScope.class, null, scope, false));
*/
            TalesResponse<ExternalMyObject> response = sendGetRequest("/sample/myobject/{value}", params, fromTranslator);

            logger.info("Exit : getReport  Status : {}", response.getTalesStatusCode() );
            ExternalMyObject myObject = response.getResponseObject();
            logger.info(myObject.getValue());
            return response;
        }
        

      private static Translator getTranslator(Class<?> theClass) {
         if (translatorCache.containsKey(theClass)){
             return translatorCache.get(theClass);
         }

         Translator translator = jsonTranslationFacility.getFromJsonElementTranslator( theClass, null );
         translatorCache.put(theClass, translator);

         return translator;
     }
      
      public static void main(String arg[]) throws Exception {
          JsonTranslationFacility jsonTranslationFacility = new JsonTranslationFacility( new com.tales.contracts.data.DataContractTypeSource( ));
          jsonTranslationFacility.registerStringTranslators( ObjectId.class, new StringToObjectIdTranslator(), new ObjectIdToStringTranslator());
          jsonTranslationFacility.registerJsonElementTranslators( BigDecimal.class, new JsonElementToStringToChainTranslator(new StringToBigDecimalTranslator()), 
                  new NumberToJsonPrimitiveTranslator());
          
          SampleClient sampleClient = new SampleClient("http://localhost:8080", jsonTranslationFacility);
          TalesResponse<ExternalMyObject> response = sampleClient.getReport();
          ExternalMyObject myObject = response.getResponseObject();
          System.out.println(myObject.getValue());

      }


}
