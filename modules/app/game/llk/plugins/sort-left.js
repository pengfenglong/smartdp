var sort_left_plugin = {
	name:"left",
	onUnion:function(oIconMap,iLines,iColumns){
		var //moveList=[],
			fromMap={},//toMap={},
			iLineIndex,
			iColumnIndex,
			iIconIndex,
			iColumnEmptyIndex;
		fromMap[0]={};
		fromMap[iLines+1]={};
		//toMap[0]={};
		//toMap[iLines+1]={};
		for(iLineIndex=1;iLineIndex<=iLines;iLineIndex++){
			iColumnEmptyIndex=1;
			fromMap[iLineIndex]={};
			//toMap[iLineIndex]={};
			for(iColumnIndex=1;iColumnIndex<=iColumns;iColumnIndex++){
				iIconIndex=oIconMap[iLineIndex][iColumnIndex];
				if(iIconIndex){
					if(iColumnIndex-iColumnEmptyIndex){
						var objFromInfo=fromMap[iLineIndex][iColumnIndex],
							objToInfo = fromMap[iLineIndex][iColumnEmptyIndex];//�ƶ���Ŀ�ĵ�
							
						//moveList.push(objInfo);
						if(objToInfo){
							//ָ���Ŀ��ش������е��ƶ�������Ҫ׷��Դͷ
							fromMap[iLineIndex][iColumnIndex]={
								iFromLineIndex:iLineIndex,
								iFromColumnIndex:iColumnIndex,
								
								iToLineIndex:iLineIndex,
								iToColumnIndex:iColumnEmptyIndex,
								
								iSourceLineIndex:objToInfo.iSourceLineIndex,
								iSourceColumnIndex:objToInfo.iSourceColumnIndex,
								
								iIconIndex:iIconIndex,
								strForward:"left",
								isAnimate:true
							};
							//������Դͷ���ƶ�Ŀ�괦��Ϊ�µĵ�ַ
							var objSourceInfo=fromMap[objToInfo.iSourceLineIndex][objToInfo.iSourceColumnIndex];
							objSourceInfo.iToLineIndex=iLineIndex;
							objSourceInfo.iToColumnIndex=iColumnIndex;
						}else {
							//ָ���Ŀ��ز��������е��ƶ����Ͳ���Ҫ׷��Դͷ
							fromMap[iLineIndex][iColumnIndex]={
								iFromLineIndex:iLineIndex,
								iFromColumnIndex:iColumnIndex,
								
								iToLineIndex:iLineIndex,
								iToColumnIndex:iColumnEmptyIndex,
								
								iSourceLineIndex:iLineIndex,
								iSourceColumnIndex:iColumnEmptyIndex,
								
								iIconIndex:iIconIndex,
								strForward:"left",
								isAnimate:true
							};
							fromMap[iLineIndex][iColumnEmptyIndex]={//to��Դͷ
								iFromLineIndex:iLineIndex,
								iFromColumnIndex: iColumnEmptyIndex,
								
								iToLineIndex:iLineIndex,
								iToColumnIndex:iColumnIndex,
								
								iSourceLineIndex:iLineIndex,
								iSourceColumnIndex:iColumnEmptyIndex,
								
								iIconIndex:iIconIndex,
								strForward:"left",
								isAnimate:true
							};
						}
						//toMap[iLineIndex][iColumnEmptyIndex]=moveList.length;
						oIconMap[iLineIndex][iColumnEmptyIndex]=iIconIndex;
						oIconMap[iLineIndex][iColumnIndex]=0;
					}
					iColumnEmptyIndex++;
				}
			}
		}
		return fromMap;
	}
};