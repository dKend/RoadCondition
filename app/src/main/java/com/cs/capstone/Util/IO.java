package com.cs.capstone.Util;

import android.os.StatFs;

import com.cs.capstone.Util.Error.IO.ErrorConditions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * Created by damia on 08-Mar-18.
 */

public final class IO {
    /**
     * Calculates the size of the passed Serializable object.
     * <p>
     *      Error Conditions:
     *          NotSerializable: throws UtilException
     *              - o doesn't inplement the serializable interface.
     *          isNull: throws UtilException
     *              - o is null.
     * </p>
     * @param o Object whose size in bytes we would like to find.
     * @return  The size of the serialized verion of o in bytes.
     * @throws UtilException
     * @throws IOException
     */
    public static float sizeof(Object o) throws UtilException, IOException {
        float ret;
        if(o != null){
            if(o instanceof java.io.Serializable){
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bout);
                out.writeObject(o);
                out.flush();
                out.close();
                ret = ((float)(bout.toByteArray().length))/(1024.f * 1024.f);
            }else{
                throw UtilException.CreateUtilException(ErrorConditions.SizeOf_NotSerializable);
            }
        }else{
            throw UtilException.CreateUtilException(ErrorConditions.SizeOf_IsNull);
        }
        return ret;
    }

    /**
     * Gets the available space in MB.
     * @param f File
     * @return float Available space in MB
     * @throws UtilException
     */
    public static float getAvailableSpace(File f) throws UtilException{
        float ret;
        if(f != null){
            StatFs stat = new StatFs(f.getPath());
            long bytes = 0;
            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2)
                bytes = (long) stat.getBlockSizeLong() * (long) stat.getAvailableBlocksLong();
            else
                bytes = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
            ret = bytes/(1024.f * 1024.f);
        }else{
            throw UtilException.CreateUtilException(ErrorConditions.GetAvailableSpace_IsNull);
        }
        return ret;
    }

    /**
     * Checks if there is sufficient space to create the serialization of the Object o.
     * @param o Object
     * @param f File
     * @return True if there is enough space, False otherwise.
     * @throws UtilException
     * @throws IOException
     */
    public static boolean isSpaceSufficient(Object o, File f) throws UtilException, IOException {
        boolean ret = false;
        try{
            ret = getAvailableSpace(f) >= sizeof(o);
        }catch(java.lang.RuntimeException e){
            //  cant access getBlockSize when not on an actual android device.
            ret = true;
        }
        return ret;
    }
}
