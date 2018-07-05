package org.vl.example.inputoutput.externalizable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExternalizablePairTest {

    @Test
    public void whenSerializingAndDeserializing_ThenObjectIsTheSame()
            throws IOException, ClassNotFoundException {
        ExternalizablePair notExternalized = new ExternalizablePair();
        notExternalized.setKey("20");
        notExternalized.setValue("Joe");

        FileOutputStream fileOutputStream
                = new FileOutputStream("yourfile.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(notExternalized);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream("yourfile.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        ExternalizablePair externalized = (ExternalizablePair) objectInputStream.readObject();
        objectInputStream.close();

        assertTrue(externalized.getKey().equals(notExternalized.getKey()));
        assertTrue(externalized.getValue().equals(notExternalized.getValue()));
    }

}