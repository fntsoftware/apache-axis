/**
 * MyServiceServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2alpha Dec 06, 2003 (10:46:24 EST) WSDL2Java emitter.
 */

package test.wsdl.date;

import test.HttpTestUtil;

public class MyServiceServiceTestCase extends junit.framework.TestCase {
    public MyServiceServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testtestdateWSDL() throws Exception {
        jakarta.xml.rpc.ServiceFactory serviceFactory = jakarta.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = HttpTestUtil.getTestEndpoint(new test.wsdl.date.MyServiceServiceLocator().gettestdateAddress() + "?WSDL");
        jakarta.xml.rpc.Service service = serviceFactory.createService(url, new test.wsdl.date.MyServiceServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1testdateGetInfo() throws Exception {
        test.wsdl.date.TestdateSoapBindingStub binding;
        try {
            MyServiceServiceLocator loc = new MyServiceServiceLocator();
            binding = (TestdateSoapBindingStub)loc.gettestdate(HttpTestUtil.getTestEndpoint(loc.gettestdateAddress()));
        }
        catch (jakarta.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        test.wsdl.date.MyBean value = null;
        value = binding.getInfo();
        // TBD - validate results
        System.out.println(value.getDate());
    }

}
