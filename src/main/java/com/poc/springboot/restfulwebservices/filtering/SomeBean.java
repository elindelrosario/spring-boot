package com.poc.springboot.restfulwebservices.filtering;

import java.util.Set;
import org.springframework.http.converter.json.MappingJacksonValue;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

// NOTE: @JsonIgnore is for static filtering
@JsonIgnoreProperties({"field1"}) // To hide fields in response
// NOTE: @JsonFilter is for dynamic filtering
@JsonFilter("SomeBeanFilter")
public class SomeBean {

  private String field1;
  private String field2;

  @JsonIgnore // To hide field in response
  private String field3;

  private String field4;

  public SomeBean(String field1, String field2, String field3, String field4) {
    super();
    this.field1 = field1;
    this.field2 = field2;
    this.field3 = field3;
    this.field4 = field4;
  }

  public String getField1() {
    return field1;
  }

  public void setField1(String field1) {
    this.field1 = field1;
  }

  public String getField2() {
    return field2;
  }

  public void setField2(String field2) {
    this.field2 = field2;
  }

  public String getField3() {
    return field3;
  }

  public void setField3(String field3) {
    this.field3 = field3;
  }

  public String getField4() {
    return field4;
  }

  public void setField4(String field4) {
    this.field4 = field4;
  }

  @Override
  public String toString() {
    return String.format("SomeBean [field1=%s, field2=%s, field3=%s, field4=%s]", field1, field2,
        field3, field4);
  }

  // NOTE: This is to create dynamic filtering
  public static MappingJacksonValue getFilteredMapping(Object object, Set<String> fields) {
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);

    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

    MappingJacksonValue mapping = new MappingJacksonValue(object);
    mapping.setFilters(filters);

    return mapping;
  }

}
