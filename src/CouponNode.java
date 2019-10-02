package CS401Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//this is the linked list class of coupon, all the coupon elements will be put into this list first.
public class CouponNode extends LLNode<Coupon> implements ItemListener, ActionListener, Runnable {

	private Coupon info;
	private CouponNode link;

	public CouponNode() {
	}

	public CouponNode(Coupon info) {
		this.info = info;
	}

	public CouponNode add(CouponNode list, CouponNode newNode) {
		CouponNode location = list;
		boolean added = false;
		while (added == false) {
			if (location.getLink() == null) {
				location.setLink(newNode);
				added = true;
			} else {
				location = location.getLink();
			}
		}
		return list;
	}

	public boolean remove(CouponNode target) {
		boolean removed = false;
		CouponNode location = this;
		while (removed == false) {
			if (location.getLink().equals(target)) {
				location.setLink(target.getLink());
				removed = true;
			} else
				location = location.getLink();
		}
		return removed;
	}

	public void setInfo(Coupon info) {
		this.info = info;
	}

	public Coupon getInfo() {
		return info;
	}

	public void setLink(CouponNode link) {
		this.link = link;
	}

	public CouponNode getLink() {
		return link;
	}

	@Override
	public void run() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}
}

class LLNode<T> {
	protected T info;
	protected LLNode<T> link;

	public LLNode() {

	}

	public LLNode(T info) {
		this.info = info;
		link = null;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public T getInfo() {
		return info;
	}

	public void setLink(LLNode<T> link) {
		this.link = link;
	}

	public LLNode<T> getLink() {
		return link;
	}
}