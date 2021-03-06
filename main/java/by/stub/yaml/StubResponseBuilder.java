package by.stub.yaml;

import by.stub.utils.ReflectionUtils;
import by.stub.yaml.stubs.StubResponse;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Alexander Zagniotov
 * @since 4/14/13, 4:54 PM
 */
final class StubResponseBuilder implements StubBuilder<StubResponse> {

   private final Map<String, Object> fieldNameAndValues;
   private String status;
   private String body;
   private File file;
   private String latency;
   private Map<String, String> headers;

   StubResponseBuilder() {
      this.status = null;
      this.body = null;
      this.file = null;
      this.latency = null;
      this.headers = new LinkedHashMap<>();
      this.fieldNameAndValues = new HashMap<>();
   }

   @Override
   public void store(final String fieldName, final Object fieldValue) {
      fieldNameAndValues.put(fieldName.toLowerCase(), fieldValue);
   }

   @Override
   public StubResponse build() throws Exception {
      ReflectionUtils.injectObjectFields(this, fieldNameAndValues);
      return new StubResponse(status, body, file, latency, headers);
   }
}
