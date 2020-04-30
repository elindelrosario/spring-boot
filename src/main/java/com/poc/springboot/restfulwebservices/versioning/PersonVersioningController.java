package com.poc.springboot.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

  // Option #1: URI Versioning; different paths
  @GetMapping("v1/person")
  public PersonV1 personV1() {
    return new PersonV1("Elin del Rosario");
  }

  @GetMapping("v2/person")
  public PersonV2 personV2() {
    return new PersonV2(new Name("Elin", "del Rosario"));
  }

  // Option #2: Request Parameter Versioning; same path but with query param of version
  @GetMapping(value = "/person/param", params = "version=1")
  public PersonV1 paramV1() {
    return new PersonV1("Elin del Rosario");
  }

  @GetMapping(value = "/person/param", params = "version=2")
  public PersonV2 paramV2() {
    return new PersonV2(new Name("Elin", "del Rosario"));
  }

  // Option #3: Header Versioning; same path but with header of X-API-VERSION
  @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
  public PersonV1 headerV1() {
    return new PersonV1("Elin del Rosario");
  }

  @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
  public PersonV2 headerV2() {
    return new PersonV2(new Name("Elin", "del Rosario"));
  }

  // Option #4: Media Type Versioning; same path but with produces using Accept header
  @GetMapping(value = "/person/produces", produces = "application/poc.app-v1+json")
  public PersonV1 producesV1() {
    return new PersonV1("Elin del Rosario");
  }

  @GetMapping(value = "/person/produces", produces = "application/poc.app-v2+json")
  public PersonV2 producesV2() {
    return new PersonV2(new Name("Elin", "del Rosario"));
  }

}
