package practice.jvm;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Memory {
    private static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long addressOf(Object o) throws Exception {
        Object[] array = new Object[] { o };
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        int addressSize = unsafe.addressSize();
        long objectAddress;
        switch (addressSize) {
            case 4:
                objectAddress = unsafe.getInt(array, baseOffset);
                break;
            case 8:
                objectAddress = unsafe.getLong(array, baseOffset);
                break;
            default:
                throw new Error("unsupported address size: " + addressSize);
        }
        return (objectAddress);
    }

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
        System.out.println("allocation1:0x00000000" + Long.toHexString(addressOf(allocation1)));
        System.out.println("allocation2:0x00000000" + Long.toHexString(addressOf(allocation2)));
        System.out.println("allocation3:0x00000000" + Long.toHexString(addressOf(allocation3)));
        System.out.println("allocation4:0x00000000" + Long.toHexString(addressOf(allocation4)));
    }

}