package ftn.upp.invertAuction.services.invertAuction;

import org.activiti.engine.FormService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiService {

    @Autowired
    private FormService formService;

    public void getFormData(String taskId){
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        for(FormProperty prop : taskFormData.getFormProperties()){
            //prop.
        }
    }
}
