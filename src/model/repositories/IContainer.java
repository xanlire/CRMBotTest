package model.repositories;

import java.util.List;
import java.util.Map;

public interface IContainer {
    
    public Map<String, String> getNameList();
    
    public Map<String, String> getNameListByIdList(List<String> idList);
}
