package com.poc.springboot.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

  @GetMapping("/filtering")
  public MappingJacksonValue retrieveSomeBean() {
    SomeBean someBean = new SomeBean("value1", "value2", "value3", "value4");
    return SomeBean.getFilteredMapping(someBean, new HashSet<>(Arrays.asList("field2")));
  }

  @GetMapping("/filtering-list")
  public MappingJacksonValue retrieveListOfSomeBeans() {
    List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3", "value04"),
        new SomeBean("value01", "value02", "value03", "value04"));
    return SomeBean.getFilteredMapping(list, new HashSet<>(Arrays.asList("field4")));
  }

}
