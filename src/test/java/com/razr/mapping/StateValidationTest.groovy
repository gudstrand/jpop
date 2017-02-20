package com.razr.mapping

import spock.lang.Specification

/**
 * Created by gary on 2/19/17.
 */
class StateValidationTest extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "test valid"() {
        when:
        String stateCode = "MN"
        then:
        StateValidation.isValid(stateCode) == true
    }

    def "test not valid"() {
        when:
        String stateCode = "MG"
        then:
        StateValidation.isValid(stateCode) == false
    }
}
