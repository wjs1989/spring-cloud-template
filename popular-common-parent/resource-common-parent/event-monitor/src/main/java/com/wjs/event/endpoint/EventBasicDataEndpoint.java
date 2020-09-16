package com.wjs.event.endpoint;

import com.wjs.event.model.EventDataManager;
import com.wjs.event.service.EventBasicDataService;
import com.wjs.event.vo.EventBasicDataResp;
import com.wjs.model.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wenjs
 */
@RestController
@RequestMapping("/event")
public class EventBasicDataEndpoint {

    @Autowired
    private EventBasicDataService eventBasicDataService;

    @GetMapping("/data")
    public BaseResult<List<EventBasicDataResp>> getEventBasicData(
            @RequestParam(name = "id", required = false) Long id
    ) {
        EventDataManager.putParamsData("id", id);

        List<EventBasicDataResp> eventBasicData = eventBasicDataService.getEventBasicData();
        return BaseResult.success(eventBasicData);
    }

}
