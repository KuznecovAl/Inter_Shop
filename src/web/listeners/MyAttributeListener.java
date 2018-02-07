package web.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MyAttributeListener  implements HttpSessionAttributeListener {
	private String counterAttr = "counter";
	public void attributeAdded(HttpSessionBindingEvent ev) {
	   String currentAttributeName = ev.getName();
 	   String urlAttr = "URL";
	   if (currentAttributeName.equals(counterAttr)) {
		Integer currentValueInt = (Integer) ev.getValue();
		System.out.println("в Session добавлен счетчик=" + currentValueInt);
	   } else if (currentAttributeName.equals(urlAttr)) {
	      StringBuffer currentValueStr = (StringBuffer)ev.getValue();
  	      System.out.println("в Session добавлен URL=" + currentValueStr);
	   } else System.out.println("добавлен новый атрибут");
	}
	public void attributeRemoved(HttpSessionBindingEvent ev) { }
	public void attributeReplaced(HttpSessionBindingEvent ev) {
		String currentAttributeName = ev.getName();
		// в случае изменений, произведенных со счетчиком,
		// выводит соответствующее сообщение
		if (currentAttributeName.equals(counterAttr)) {
			Integer currentValueInt = (Integer) ev.getValue();
			System.out.println("В Session заменен cчетчик=" + currentValueInt);
		}
	}
}