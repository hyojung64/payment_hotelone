package hotelone;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="PaymentHistory_table")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long cardNo;
    private String status;

    @PostPersist
    public void onPostPersist(){
        PaymentApproved paymentApproved = new PaymentApproved();
        paymentApproved.setStatus("Pay Approved!!");
        BeanUtils.copyProperties(this, paymentApproved);
        paymentApproved.publishAfterCommit();
    }
    
    
    @PrePersist
    public void onPrePersist()
    {
        try{
            Thread.currentThread().sleep((long) (400+Math.random()*220));
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    
    @PostUpdate
    public void onPostUpdate() {
    	PaymentCanceled paymentCanceled = new PaymentCanceled();
    	BeanUtils.copyProperties(this, paymentCanceled);
    	paymentCanceled.publishAfterCommit();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
