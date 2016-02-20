package com.smartdp.core.tree;

import com.smartdp.core.tree.exception.UncodeException;

/**
 * 负责业务对象解码,分解出ID和parentID
 * @author pengfenglong
 * 
 */
public interface IUserDataUncoder {
  public Object getID(Object pUserData) throws UncodeException;
  public Object getParentID(Object pUserData) throws UncodeException;
} 
