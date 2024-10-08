package org.kosa.hello.page;

import java.util.List;

import org.kosa.hello.todo.dto.TodoDTO;

import lombok.Data;

@Data
public class PageResponseDTO<E> {
	private int page;
	private int size = 10;
	
	//페이지 시작 번호 
	private int begin; 
	private int end; 
	
	//이전 다음 버튼 출력 여부 
	private boolean prev;
	private boolean next;
	
	private List<E> list;
	
	public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> list, int total) {
		this.page = pageRequestDTO.getPage();
		this.size = pageRequestDTO.getSize();
		this.list = list;
		
		this.end = (int) Math.ceil(page/10.0) * 10;
		this.begin = end - 9;
		
		//최종 페이지 값 보정 
		int last = (int) Math.ceil(total / (float)size);
		this.end = last < this.end ? last : this.end;  
	
		this.prev = this.begin > 1;
		this.next = total > this.end * this.size; 
		
	}
	
}
