package bh.gov.cio.gbs.systemevent;

import org.springframework.context.ApplicationEvent;

import bh.gov.cio.gbs.model.MemberHistory;

public class MemberEvent extends ApplicationEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5506161692202945902L;

	public MemberEvent(MemberHistory memberHistory) {
		super(memberHistory);
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public MemberHistory getMemberHistory() {
        return (MemberHistory) getSource();
    }

}
