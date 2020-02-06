package org.muque.mold.codes;

import org.muque.mold.codes.ctx.AppContext;
import org.muque.mold.codes.ctx.ConsoleListener;
import org.muque.mold.codes.service.GenCodesService;

public class AppMain {

	public static void main(String[] args) {
		try {
			AppContext.init();
			System.out.println("startup success.");

			GenCodesService svc = AppContext.getSpringContext().getBean("genCodesService",
					GenCodesService.class);
			svc.excute();

			ConsoleListener.listenning(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
