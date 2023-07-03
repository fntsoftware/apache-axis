package test.wsdl.parameterOrder;

import test.HttpTestUtil;

/**
* This class is taken from the generated TestCase.  The generated test case is still generated
* to verify that the generated test case is always compilable.  THIS test case exists
* because I know this is correct.  If the generation of the bindings changes, it's likely that
* the generation of the TestCase will change as well, so we wouldn't know whether they changed
* for the worse if they all changed the same.  This test case should fail to compile if generated
* stuff changed for the worse.
*/

public class VerifyTestCase extends junit.framework.TestCase {
    public VerifyTestCase(String name) {
        super(name);
    }

    public void testParameterOrder() throws Exception {
        test.wsdl.parameterOrder.ParameterOrderTest binding;
        try {
            ParameterOrderServiceLocator loc = new ParameterOrderServiceLocator();
            binding = loc.getParameterOrder(HttpTestUtil.getTestEndpoint(loc.getParameterOrderAddress()));
        } catch (jakarta.xml.rpc.ServiceException jre) {
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);
        try {
            binding.oneIn(0);
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.oneInout(new jakarta.xml.rpc.holders.IntHolder(0));
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.oneOut(new jakarta.xml.rpc.holders.IntHolder());
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.fiveInNoOrder(0, 0, 0, 0, 0);
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.fiveInoutNoOrder(new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0));
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.fiveOutNoOrder(new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder());
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.fiveIn(0, 0, 0, 0, 0);
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.fiveInout(new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0));
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.fiveOut(new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder());
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            int value = -3;
            value = binding.someInoutPartialOrder1(new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0));
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.someInoutPartialOrder2(new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(0), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder());
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            binding.fourOutPartialOrder(new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder());
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
        try {
            int value = -3;
            value = binding.oneReturn(new jakarta.xml.rpc.holders.IntHolder(), new jakarta.xml.rpc.holders.IntHolder());
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
    }
}

