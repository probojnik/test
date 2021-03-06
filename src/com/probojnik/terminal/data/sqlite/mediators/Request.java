package com.probojnik.terminal.data.sqlite.mediators;

/**
 * @author Stanislav Shamji
 */
public class Request {
    private final RequestType requestType;
    private final String department;
    private final String groupID;
    private final String ovirServiceID;
    private final String parent;

    public static Request createTerminalServicesRequest() {
        return new Request(RequestType.TerminalServices, "", "", "", "");
    }

    public static Request createGroupsListRequest(String department, String parent) {
        return new Request(RequestType.GroupsList, department, parent, "", "");
    }

    public static Request createServiceListRequest(String department, String groupID) {
        return new Request(RequestType.ServiceList, department, "", groupID, "");
    }

    public static Request createGetParamsRequest(String ovirServiceID) {
        return new Request(RequestType.GetParams, "", "", "", ovirServiceID);
    }

    private Request(RequestType requestType, String department, String parent, String groupID, String ovirServiceID) {
        this.requestType = requestType;
        this.department = department;
        this.groupID = groupID;
        this.ovirServiceID = ovirServiceID;
        this.parent = parent;
    }

    public RequestType getNextRequestType() {
        switch (requestType) {
            case TerminalServices:
                return RequestType.GroupsList;
            case GroupsList:
                return RequestType.ServiceList;
            case ServiceList:
                return RequestType.GetParams;
        }
        return null;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getDepartment() {
        return department;
    }

    public String getGroupID() {
        return groupID;
    }

    public String getOvirServiceID() {
        return ovirServiceID;
    }

    public String getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "requestType = " + requestType + ", department = " + department + ", groupID = " + groupID + ", ovirServiceID = " + ovirServiceID + ", parent = " + parent;
    }

}
