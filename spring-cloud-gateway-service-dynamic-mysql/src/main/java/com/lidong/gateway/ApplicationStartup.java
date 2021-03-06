package com.lidong.gateway;

import com.lidong.gateway.service.GatewayDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationRunner {

    @Autowired
    private GatewayDefineService gatewayDefineService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        GatewayDefine definition = new GatewayDefine();
//        definition.setUri("http://www.163.com");
//        definition.setId(UuidUtil.generateUuid());
//        List<PredicateDefinition> predicateDefinitions = new ArrayList<>();
//
//        PredicateDefinition predicateDefinition = new PredicateDefinition("Path=/163/**");
////        predicateDefinition.setName("Path");
////        Map<String, String> args1 = new HashMap<>();
//////        args1.put("_genkey_0","/163/**");
////        predicateDefinition.setArgs(args1);
////        predicateDefinition.addArg();
//        predicateDefinitions.add(predicateDefinition);
//        String ps = JSON.toJSONString(predicateDefinitions);
//        definition.setPredicates(ps);
//
//        List<FilterDefinition> filterDefinitions = new ArrayList<>();
//        FilterDefinition filterDefinition = new FilterDefinition();
//        filterDefinitions.add(filterDefinition);
//        if (filterDefinitions != null) {
//            definition.setFilters(JSON.toJSONString(filterDefinitions));
//        }
//
//        gatewayDefineService.save(definition);
        gatewayDefineService.loadRouteDefinition();
    }
}
