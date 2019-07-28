package com.example.processControllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.model.ProcessDefinition;
import org.kie.api.runtime.query.QueryContext;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class JbpmController {
	
	 @Autowired            
	    private DeploymentService deploymentService;
	    
	    @Autowired
	    private RuntimeDataService runtimeDataService;
	    
	    @Autowired
	    private ProcessService processService;
	
	@RequestMapping(value="/jbpm", method=RequestMethod.GET)
	public void jbpmFlowCheck() {
		//return jbpmService.jbpmFlowCheck(request);
		
		 KModuleDeploymentUnit unit = null;
//	      if (strings.length > 0) {
	         
	          
	          unit = new KModuleDeploymentUnit("org.mastertheboss.kieserver", "hello-kie-server", "1.0");
	          deploymentService.deploy(unit);
//	      }
	      Collection<ProcessDefinition> processes = runtimeDataService.getProcesses(new QueryContext());
	      for (ProcessDefinition def : processes) {
	      }
	      
	      if (unit != null && !processes.isEmpty()) {
	          String processId = processes.iterator().next().getId();
	          long processInstanceId = processService.startProcess(unit.getIdentifier(), processId);
	          
	          processService.abortProcessInstance(processInstanceId);
	      }
	      
	}
	

}
