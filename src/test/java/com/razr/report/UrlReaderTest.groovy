package com.razr.report

import com.razr.mapping.CityDTO
import com.razr.mapping.CityMapper
import spock.lang.Shared

import java.util.stream.Stream

/**
 * Test of the UrlReader class
 */
class UrlReaderTest extends spock.lang.Specification {

    @Shared
            urlAddress = "https://dl.dropboxusercontent.com/u/2436323/cities.jsonl"
    @Shared
            fileAddress = "file:src//test//resources//urlreader//filetest.jsonl"
    @Shared
            filePrefix = "file:src//test//resources//urlreader//"
    @Shared
            urlcompressed = "https://dl.dropboxusercontent.com/u/7002771/allrecs.jsonl.gz"

    @Shared
            reader = new UrlReader()

    def setupSpec() {

    }

    def setup() {

    }

    List<CityDTO> createList(String url) {
        CityMapper mapper = new CityMapper()
        List<CityDTO> cities = new ArrayList<>()
        BufferedReader br = reader.createReader(url)
        Stream<String> stream = br.lines()
        stream.each { json -> cities.add(mapper.map(json)) }
        br.close()
        return cities
    }

    def "read from url"() {
        when:
        List<CityDTO> cities = createList(urlAddress)
        then:
        cities.size() == 11
    }

    def "read from file"() {
        when:
        List<CityDTO> cities = createList(fileAddress)
        then:
        cities.size() == 5
    }

    def "windows file"() {
        when:
        List<CityDTO> cities = createList(filePrefix + "crlfline.jsonl")
        then:
        cities.size() == 5
    }

    def "no ending cr"() {
        when:
        List<CityDTO> cities = createList(filePrefix + "noendingcr.jsonl")
        then:
        cities.size() == 3
    }

    def "compresed"() {
        when:
        List<CityDTO> cities = createList(urlcompressed)
        then:
        cities.size() == 11
    }

}
