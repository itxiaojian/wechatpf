package com.sliu.framework.app.common.uuid;
///**        
// * Copyright (c) 2013 by 苏州科大国创信息技术有限公司.    
// *
// * StrongUuidGenerator.java Create on 2013-5-30 下午8:59:16 
// */
//package com.sliu.framework.app.common.uuid;
//
//import com.fasterxml.uuid.EthernetAddress;
//import com.fasterxml.uuid.Generators;
//import com.fasterxml.uuid.impl.TimeBasedGenerator;
//
///**
// * 
// * @author <a href="mailto:bsli@ustcinfo.com">li.binsong</a>
// * 
// */
//public class StrongUuidGenerator {
//
//	protected static TimeBasedGenerator timeBasedGenerator;
//
//	static {
//		timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
//	}
//
//
//	public static String getNextId() {
//		return timeBasedGenerator.generate().toString();
//	}
//}
