package it.mohanrc.socialmedia.controller;

import it.mohanrc.socialmedia.model.PersonV1;
import it.mohanrc.socialmedia.model.PersonV2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningResource {

    /* Versoning Based on URI
    * In request URI use v1/person
    * */

    @GetMapping("v1/person")
    public PersonV1 personV1URI() {
        return new PersonV1("Mohan Raj");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2URI() {
        return new PersonV2("Mohan", "Raj");
    }

    /* Versoning Based on Params
    * In request URI use person/param?version=1
    * */

    @GetMapping(value = "person/param", params = "version=1")
    public PersonV1 personV1Param() {
        return new PersonV1("Mohan Raj");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public PersonV2 personV2Param() {
        return new PersonV2("Mohan", "Raj");
    }

    /* Versoning Based on Headers
    * In header X-API-VERSION = 1
    * */

    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 personV1Header() {
        return new PersonV1("Mohan Raj");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 personV2Header() {
        return new PersonV2("Mohan", "Raj");
    }

    /* Versoning Based on Content Negotiation or Produces or MIME Type versioning
    * In header Accept = application/vnd.myapp.person.v1+json
    * */

    @GetMapping(value = "person/produces", produces = "application/vnd.myapp.person.v1+json")
    public PersonV1 personV1Produces() {
        return new PersonV1("Mohan Raj");
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.myapp.person.v2+json")
    public PersonV2 personV2Produces() {
        return new PersonV2("Mohan", "Raj");
    }
}
