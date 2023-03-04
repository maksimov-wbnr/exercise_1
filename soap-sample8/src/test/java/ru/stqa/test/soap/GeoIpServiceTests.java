package ru.stqa.test.soap;


import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testGeoIp(){
    String myLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("188.168.136.111");
    System.out.println(myLocation);
    Assert.assertEquals(myLocation,"<GeoIP><Country>RU</Country><State>23</State></GeoIP>");
  }
}
