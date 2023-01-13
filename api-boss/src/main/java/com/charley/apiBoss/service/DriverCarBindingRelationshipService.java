package com.charley.apiBoss.service;

import com.charley.apiBoss.remote.ServiceDriverUserClient;
import com.charley.internalcommon.dto.DriverCarBindingRelationship;
import com.charley.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship){
        return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }
}
