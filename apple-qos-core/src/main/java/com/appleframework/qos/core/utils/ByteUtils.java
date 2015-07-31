package com.appleframework.qos.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by haiyang on 15/7/22.
 */
public class ByteUtils {

    private static KryoSerializer kryoSerializer;
    static {
        kryoSerializer = new KryoSerializer();
        try {
            kryoSerializer.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] toBytes(Object obj) {
        byte[] bytes = null;
        try {
            bytes = kryoSerializer.serialize(obj);
//            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            ObjectOutputStream oo = new ObjectOutputStream(bo);
//            oo.writeObject(obj);
//
//            bytes = bo.toByteArray();
//
//            bo.close();
//            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            obj = kryoSerializer.deserialize(bytes);
//            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
//            ObjectInputStream oi = new ObjectInputStream(bi);
//
//            obj = oi.readObject();
//            bi.close();
//            oi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


}
