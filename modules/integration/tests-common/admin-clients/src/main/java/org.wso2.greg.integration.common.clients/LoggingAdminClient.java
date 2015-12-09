/*
*Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/

package org.wso2.greg.integration.common.clients;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.logging.admin.stub.LoggingAdminException;
import org.wso2.carbon.logging.admin.stub.LoggingAdminStub;

import java.rmi.RemoteException;

public class LoggingAdminClient {

    private static final Log log = LogFactory.getLog(LoggingAdminClient.class);
    private final String serviceName = "LoggingAdmin";
    private String endpoint = null;
    private LoggingAdminStub loggingAdminStub;

    public LoggingAdminClient(String backEndUrl, String sessionCookie) throws AxisFault {
        this.endpoint = backEndUrl + serviceName;
        loggingAdminStub = new LoggingAdminStub(this.endpoint);
        AuthenticateStub.authenticateStub(sessionCookie, loggingAdminStub);
    }

    public LoggingAdminClient(String backEndURL, String userName, String password) throws AxisFault {
        this.endpoint = backEndURL + serviceName;
        loggingAdminStub = new LoggingAdminStub(this.endpoint);
        AuthenticateStub.authenticateStub(userName, password, loggingAdminStub);
    }

    /**
     * Update the System Logging configuration. The global logging level & the package which logging to be enabled
     * will be updated by this method.
     *
     * @param loggerName The package which logging to be enabled.
     * @param logLevel   The global log level to be set.
     * @param additivity
     * @param persist    true - indicates persist these changes to the DB; false - indicates make
     *                   changes only in memory and do not persist the changes to DB.
     *
     * @return Status of the function.
     *
     * @throws RemoteException
     * @throws LoggingAdminException
     */
    public boolean updateLoggerData(String loggerName, String logLevel, boolean additivity, boolean persist)
            throws RemoteException, LoggingAdminException {

        loggingAdminStub.updateLoggerData(loggerName, logLevel, additivity, persist);
        return true;
    }

}
