package com.cdilansrod.apirestlansrod.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("deprecation")
public class Utility {

	 public static final Log log = LogFactory.getLog(Utility.class); 

    //Base URL
    public static final String URL_API_LANSROD = "/api";
    public static final String URL_API_LANSROD_AUTH = "/auth";
    public static final String MESSAGE_SUCCESS = "Opération réussie";
    public static final String MESSAGE_FAILED = "Operation échouée";
    public static final String CODE_SUCCESS = "00";
    public static final String CODE_FAILED = "99";
}
