package test.holders.holders;

import javax.xml.rpc.holders.Holder;

public final class BookHolder implements Holder {
    public test.holders.Book value;

    public BookHolder() {
    }

    public BookHolder(test.holders.Book value) {
        this.value = value;
    }

}
