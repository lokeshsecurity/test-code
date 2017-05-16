package bh.gov.cio.gbs.systemevent;

import org.springframework.context.ApplicationEvent;

import bh.gov.cio.gbs.model.Board;
import bh.gov.cio.gbs.model.BoardHistrory;

public class BoardEvent extends ApplicationEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6099578254069762425L;

	public BoardEvent(BoardHistrory boardHistrory) {
		super(boardHistrory);
		
	}
	
	public BoardHistrory getBoardHistrory() {
        return (BoardHistrory) getSource();
    }

	

}
