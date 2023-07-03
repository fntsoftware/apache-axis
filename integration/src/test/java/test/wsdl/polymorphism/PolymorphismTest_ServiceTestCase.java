/**
 * PolymorphismTest_ServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package test.wsdl.polymorphism;

import test.HttpTestUtil;

public class PolymorphismTest_ServiceTestCase extends junit.framework.TestCase {
    public PolymorphismTest_ServiceTestCase(String name) {
        super(name);
    }

    public void testPolymorphismTestWSDL() throws Exception {
        jakarta.xml.rpc.ServiceFactory serviceFactory = jakarta.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = HttpTestUtil.getTestEndpoint(new test.wsdl.polymorphism.PolymorphismTest_ServiceLocator().getPolymorphismTestAddress() + "?WSDL");
        jakarta.xml.rpc.Service service = serviceFactory.createService(url, new test.wsdl.polymorphism.PolymorphismTest_ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1PolymorphismTestGetBAsA() throws Exception {
        test.wsdl.polymorphism.PolymorphismTest_PortType binding;
        try {
            PolymorphismTest_ServiceLocator loc = new PolymorphismTest_ServiceLocator();
            binding = loc.getPolymorphismTest(HttpTestUtil.getTestEndpoint(loc.getPolymorphismTestAddress()));
        }
        catch (jakarta.xml.rpc.ServiceException jre) {
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        try {
            test.wsdl.polymorphism.A value = null;

            // Passing false here gets us a "B"
            value = binding.getBAsA(false);

            // Check out the return value for correctness.
            assertTrue("Return value wasn't a 'B'!", value instanceof B);
            B myB = (B)value;
            assertEquals("B field didn't match",
                         PolymorphismTestSoapImpl.B_TEXT,
                         myB.getB());
            assertEquals("A field didn't match",
                         PolymorphismTestSoapImpl.A_TEXT,
                         myB.getA());
        }
        catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re);
        }
    }

    public void test1PolymorphismTestGetCAsA() throws Exception {
        test.wsdl.polymorphism.PolymorphismTest_PortType binding;
        try {
            PolymorphismTest_ServiceLocator loc = new PolymorphismTest_ServiceLocator();
            binding = loc.getPolymorphismTest(HttpTestUtil.getTestEndpoint(loc.getPolymorphismTestAddress()));
        }
        catch (jakarta.xml.rpc.ServiceException jre) {
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        try {
            test.wsdl.polymorphism.A value = null;

            // Passing true here gets us a "C" on the server side, but since
            // there's no type mapping for that we should just get the "A"
            // part.
            value = binding.getBAsA(true);

            // Check out the return value for correctness.
            assertTrue("Return value wasn't an 'A'!", value instanceof A);
            assertEquals("A field didn't match",
                         PolymorphismTestSoapImpl.A_TEXT,
                         value.getA());
        }
        catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re);
        }
    }

}
