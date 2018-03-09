package com.cs.capstone.Util;

import com.cs.capstone.Util.Error.IO.ErrorConditions;

/**
 * Created by damia on 08-Mar-18.
 */
public class UtilException extends Exception {
    private UtilException(String m) {
        super(m);
    }

    /**
     * Creates an instance of the UtilException class without throwing the exception
     * <p>
     *      Calling function can create the exception like any other, but without needing to have the
     *      'new' token in front of a constructor. For example, we would throw a UtilException for
     *      the 'NotSerializable' case of sizeof by using the following line:
     *
     *                      throw CreateUtilException(NotSerializable);
     *
     * </p>
     * <p>
     *     TODO: Refactor the CreateUtilException function to handle a wide range of error enums.
     * </p>
     * @param e SizeOfErrorCondition, An Enum defined in the Error package and Error's subpackages.
     * @return UtilException
     */
    public static UtilException CreateUtilException(ErrorConditions e){
        UtilException ret = null;
        switch (e) {
            case SizeOf_NotSerializable:
                ret = new UtilException("[["+e.toString()+"]]: The Object parameter of the sizeof function was not an instance of the Serializable interface.");
                break;
            case SizeOf_IsNull:
                ret = new UtilException("[["+e.toString()+"]]: The Object parameter of the sizeof function was null.");
                break;
            case GetAvailableSpace_IsNull:
                ret = new UtilException("[["+e.toString()+"]]: The File parameter of the getAvailableSpace function was null.");
                break;
        }
        return ret;
    }
}
