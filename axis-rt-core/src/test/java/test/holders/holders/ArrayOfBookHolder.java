package test.holders.holders;

import javax.xml.rpc.holders.Holder;

public final class ArrayOfBookHolder implements Holder {
    public test.holders.ArrayOfBook value;

    public ArrayOfBookHolder() {
    }

    public ArrayOfBookHolder(test.holders.ArrayOfBook value) {
        this.value = value;
    }

}
