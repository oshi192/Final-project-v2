package controller.command;

import model.Pagination;
import model.dao.entity.Discount;
import model.dao.entity.User;
import model.service.DiscountService;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandDiscount  implements Command {
    private DiscountService discountService = new DiscountService();
    private Logger logger = Logger.getLogger(CommandDiscount.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (POST_METHOD.equals(request.getMethod())) {
            executePost(request);
        }
        Pagination pagination = new Pagination();
        pagination.paginate(request, discountService);
        return ResourceBundleManager.getPath(ResourceBundleManager.PAGE_DISCOUNT);
    }

    private void executePost(HttpServletRequest request) {
        String add = request.getParameter("add");
        String remove = request.getParameter("remove");
        if(!(add == null || add.isEmpty())){
            logger.info("add");
            Optional<Discount> discount = createDiscountFromRequest(request);
            if(discount.isPresent())discountService.create(discount.get());
        }
        if(!(remove == null || remove.isEmpty())){
            discountService.deleteById(Integer.parseInt(request.getParameter("remove")));
        }
    }

    private Optional<Discount> createDiscountFromRequest(HttpServletRequest request) {
        String startTime = request.getParameter("discount-add-startTime");
        String endTime = request.getParameter("discount-add-endTime");
        String sumWithUserDisc = request.getParameter("discount-add-sumWithUserDisc");
        String text = request.getParameter("discount-add-text");
        String text_uk = request.getParameter("discount-add-text_uk");
        Map<String,String> messages = new HashMap<>();
        if( startTime == null || startTime.isEmpty()) messages.put("MSGstartTime",ResourceBundleManager.getMessage(ResourceBundleManager.DISCOUNT_ERROR_START_TIME));
        if( endTime == null || endTime.isEmpty()) messages.put("MSGendTime",ResourceBundleManager.getMessage(ResourceBundleManager.DISCOUNT_ERROR_END_TIME));
        if( text == null || text.isEmpty()) messages.put("MSGtext",ResourceBundleManager.getMessage(ResourceBundleManager.DISCOUNT_ERROR_TEXT));
        if( text_uk == null || text_uk.isEmpty()) messages.put("MSGtext_uk",ResourceBundleManager.getMessage(ResourceBundleManager.DISCOUNT_ERROR_TEXT_UK));

        if(messages.isEmpty()){
            Discount discount = new Discount();
            discount.setStartTime(LocalDate.parse(startTime));
            discount.setEndTime(LocalDate.parse(endTime));
            discount.setSumWithUserDisc(( sumWithUserDisc == null || sumWithUserDisc.isEmpty())?false:true);
            discount.setText(text);
            discount.setText_uk(text_uk);
            discount.setAuthorId(((User)(request.getAttribute("user"))).getId());
            logger.info(discount);
            return Optional.of(discount);
        }else{
            request.setAttribute("messages",messages);
            logger.info("there are some errors: "+messages);
            return Optional.empty();
        }
    }
}
