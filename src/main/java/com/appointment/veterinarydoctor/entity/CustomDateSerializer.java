package com.appointment.veterinarydoctor.entity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date> {
    private static SimpleDateFormat formatter 
    = new SimpleDateFormat("dd-MM-yyyy");

  public CustomDateSerializer() { 
      this(null); 
  } 

  public CustomDateSerializer(Class<Date> t) {
      super(t); 
  }

  @Override
  public void serialize(
    Date value, JsonGenerator gen, SerializerProvider arg2) 
    throws IOException, JsonProcessingException {
      gen.writeString(formatter.format(value));
  }

}
