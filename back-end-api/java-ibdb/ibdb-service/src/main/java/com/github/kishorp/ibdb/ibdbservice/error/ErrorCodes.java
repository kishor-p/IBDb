package com.github.kishorp.ibdb.ibdbservice.error;

/**
 * <li> List of all Error Codes and their messages </li>
 * <li> Error Code Format:
 *     <code><ul>
 *         <li> ERR_ </li>
 *         <li>[SERVICE IDENTIFIER]_ </li>
 *         <li>[HTTP STATUS CODE]_ </li>
 *         <li>[ERROR CODE SEQUENCE]  </li>
 *      </ul></code>
 * </li>
 * <li> Message Format: <code>[ERROR_CODE]#[ERROR TITLE]#[ERROR DETAIL with PLACEHOLDERS for PARAMS]</code> </li>
 *
 * <li> <b>USAGE</b>: Used when a custom {@link IbdbServiceException} is thrown </li>
 */
public interface ErrorCodes {
       String ERR_01_409_01 = "ERR_01_409_01#Error Saving Publisher#Already an Publisher with same NAME Exists";
       String ERR_01_409_02 = "ERR_01_409_02#Error Saving Publisher#Already an Publisher with same EMAIL Exists";
       String ERR_01_404_03 = "ERR_01_404_03#Error Finding Publisher#Can not find any publisher with ID: {0}";


       String ERR_02_409_01 = "ERR_02_409_01#Error Saving Author#Already an Author with same NAME Exists";
       String ERR_02_409_02 = "ERR_02_409_02#Error Saving Author#Already an Author with same EMAIL Exists";
       String ERR_02_404_03 = "ERR_02_404_03#Error Finding Author#Can not find any Author with ID: {0}";
}
