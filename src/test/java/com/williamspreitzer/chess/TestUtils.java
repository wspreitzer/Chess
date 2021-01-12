package com.williamspreitzer.chess;

import java.awt.Component;
import java.awt.Container;

public class TestUtils {

	public static Component getChildNamed(Component parent, String name) {
		Component retVal = null;
		if (parent instanceof Container) {
			if(name.equals(parent.getName())) {
				retVal = parent;
			} else {
				Component[] children = ((Container) parent).getComponents();
				for (int i = 0; i< children.length; i++) {
					Component child = getChildNamed(children[i], name);
					if (child != null) {
						retVal = child;
					}
				}
			}
		}
		return retVal;
	}
}