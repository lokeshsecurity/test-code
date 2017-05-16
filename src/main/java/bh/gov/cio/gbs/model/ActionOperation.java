package bh.gov.cio.gbs.model;

public enum ActionOperation {
	
	ADD("إضافة") ,
	UPDATE("تعديل") ,
	RENEW ("تجديد") ,
	NOT_UPDATED("لم يتم التعديل"),
	EXPIRE("منتهي");
	

	private String nameAr;

	ActionOperation(String nameAr) {
        this.nameAr = nameAr;
    }

    public String nameAr() {
        return nameAr;
    }

    
	public static ActionOperation getActionOperation(
			String action) {
		if (action != null) {
			if (action.equalsIgnoreCase(
					ActionOperation.ADD.name()))
				return ActionOperation.ADD;
			else if (action.equalsIgnoreCase(
					ActionOperation.UPDATE.name()))
				return ActionOperation.UPDATE;
			else if(action.equalsIgnoreCase(
					ActionOperation.RENEW.name()))
				return ActionOperation.RENEW;
			else if(action.equalsIgnoreCase(
					ActionOperation.NOT_UPDATED.name()))
				return ActionOperation.NOT_UPDATED;
			else if(action.equalsIgnoreCase(
					ActionOperation.EXPIRE.name()))
				return ActionOperation.EXPIRE;
		}
		return null;
	}

}
