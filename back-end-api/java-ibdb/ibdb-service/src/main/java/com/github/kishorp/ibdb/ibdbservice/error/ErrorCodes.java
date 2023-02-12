package com.github.kishorp.ibdb.ibdbservice.error;

public interface ErrorCodes {
       String ERR_01_409_01 = "ERR_01_409_01#Error Saving Publisher#Already an Publisher with same NAME Exists";
       String ERR_01_409_02 = "ERR_01_409_02#Error Saving Publisher#Already an Publisher with same EMAIL Exists";

       String ERR_01_404_03 = "ERR_01_404_03#Error Finding Publisher#Cant find any publisher with ID: {0}";
}
