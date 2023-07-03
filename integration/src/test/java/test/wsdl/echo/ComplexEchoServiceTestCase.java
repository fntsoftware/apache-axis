/**
 * ComplexEchoServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package test.wsdl.echo;

import test.HttpTestUtil;

public class ComplexEchoServiceTestCase extends junit.framework.TestCase {
    public ComplexEchoServiceTestCase(String name) {
        super(name);
    }

    public void testComplexEchoServiceWSDL() throws Exception {
        jakarta.xml.rpc.ServiceFactory serviceFactory = jakarta.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = HttpTestUtil.getTestEndpoint(new test.wsdl.echo.ComplexEchoServiceLocator().getComplexEchoServiceAddress() + "?WSDL");
        jakarta.xml.rpc.Service service = serviceFactory.createService(url, new test.wsdl.echo.ComplexEchoServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1ComplexEchoServiceEcho() throws Exception {
        test.wsdl.echo.Echo binding;
        try {
            ComplexEchoServiceLocator loc = new ComplexEchoServiceLocator();
            binding = loc.getComplexEchoService(HttpTestUtil.getTestEndpoint(loc.getComplexEchoServiceAddress()));
        }
        catch (jakarta.xml.rpc.ServiceException jre) {
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        try {
            test.wsdl.echo.MyComplexType complexType = new test.wsdl.echo.MyComplexType();
            test.wsdl.echo.holders.MyComplexTypeHolder complexTypeHolder = 
                    new test.wsdl.echo.holders.MyComplexTypeHolder(complexType);  
            binding.echo(complexTypeHolder);
            assertTrue(complexTypeHolder.value.getSimpleItem().equals("MY_SIMPLE_ITEM"));
        }
        catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re);
        }
    }

    public void test2ComplexEchoServiceEcho2() throws Exception {
        test.wsdl.echo.ComplexEchoServiceSoapBindingStub binding;
        try {
            ComplexEchoServiceLocator loc = new ComplexEchoServiceLocator();
            binding = (ComplexEchoServiceSoapBindingStub)loc.getComplexEchoService(HttpTestUtil.getTestEndpoint(loc.getComplexEchoServiceAddress()));
        }
        catch (jakarta.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);
        // Time out after a minute
        binding.setTimeout(60000);
        test.wsdl.echo.MyComplexType2 request = new test.wsdl.echo.MyComplexType2();
        request.setUsername("xxx");
        request.setPassword("yyy");
        request.setOptions(new NamedValue[]{
            new NamedValue("dummy1", "dummy_val1"),
            new NamedValue("dummy2",
                    new NamedValueSet (new NamedValue[]{
                        new NamedValue("dummy2-1", "val2-1"),
                        new NamedValue("dummy2-2", new Integer(314))
                    }))
        });
        // Test operation
        test.wsdl.echo.NamedValue[] value = null;
        value = binding.echo2(request);
        // TBD - validate results
    }

    public void test2ComplexEchoServiceEcho21() throws Exception {
        test.wsdl.echo.ComplexEchoServiceSoapBindingStub binding;
        try {
            ComplexEchoServiceLocator loc = new ComplexEchoServiceLocator();
            binding = (ComplexEchoServiceSoapBindingStub)loc.getComplexEchoService(HttpTestUtil.getTestEndpoint(loc.getComplexEchoServiceAddress()));
        }
        catch (jakarta.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);
        // Time out after a minute
        binding.setTimeout(60000);
        test.wsdl.echo.MyComplexType2 request = new test.wsdl.echo.MyComplexType2();
        request.setUsername("xxx");
        request.setPassword("yyy");
        request.setOptions(new NamedValue[]{
            new NamedValue("dummy1", "dummy_val1"),
            new NamedValue("dummy2", new NamedValue[]{
                new NamedValue("dummy2-1", "val2-1"),
                new NamedValue("dummy2-2", new Integer(314))
            })
        });
        // Test operation
        test.wsdl.echo.NamedValue[] value = null;

        value = binding.echo2(request);
        // TBD - validate results
    }
}
