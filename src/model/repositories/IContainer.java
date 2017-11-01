/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.repositories;


import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public interface IContainer {
    
    public Map<String, String> getNameList();
    
    public Map<String, String> getNameListByIdList(List<String> idList);
}
