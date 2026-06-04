package prj.educatrix.main.repository;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class QARepository {

    private Map<String, String> qaMap = new HashMap<>();

    @PostConstruct
    public void initialize() {
        qaMap.put("xin chào", "Xin chào! Tôi là trợ lý ảo của Educatrix. Tôi có thể giúp gì cho bạn?");
        qaMap.put("hello", "Xin chào! Tôi là trợ lý ảo của Educatrix. Tôi có thể giúp gì cho bạn?");
        qaMap.put("hi", "Xin chào! Tôi là trợ lý ảo của Educatrix. Tôi có thể giúp gì cho bạn?");

        qaMap.put("khóa học", "Educatrix cung cấp nhiều khóa học đa dạng như lập trình, tiếng Anh, kỹ năng mềm. Bạn quan tâm đến lĩnh vực nào?");
        qaMap.put("học phí", "Học phí các khóa học dao động từ 500,000đ đến 5,000,000đ tùy theo nội dung và thời lượng. Bạn có thể xem chi tiết tại trang Khóa học.");

        qaMap.put("đăng ký", "Để đăng ký khóa học, bạn cần tạo tài khoản trên hệ thống và làm theo các bước hướng dẫn. Bạn cần hỗ trợ đăng ký tài khoản không?");
        qaMap.put("tài khoản", "Để tạo tài khoản, vui lòng nhấp vào nút Đăng ký ở góc phải màn hình và điền thông tin cá nhân.");

        qaMap.put("thanh toán", "Educatrix hỗ trợ thanh toán qua chuyển khoản ngân hàng, ví điện tử (MoMo, ZaloPay) và thẻ tín dụng/ghi nợ.");
        qaMap.put("hoàn tiền", "Chính sách hoàn tiền: bạn có thể yêu cầu hoàn tiền trong vòng 7 ngày đầu tiên nếu không hài lòng với khóa học.");

        qaMap.put("lỗi kỹ thuật", "Nếu bạn gặp lỗi kỹ thuật, vui lòng gửi email đến support@educatrix.com hoặc gọi số hotline 1900xxxx.");
        qaMap.put("quên mật khẩu", "Bạn có thể đặt lại mật khẩu bằng cách nhấp vào liên kết 'Quên mật khẩu' trên trang đăng nhập.");

        qaMap.put("chứng chỉ", "Học viên sẽ nhận được chứng chỉ hoàn thành khóa học sau khi hoàn tất tất cả bài kiểm tra và đạt điểm tối thiểu 70%.");

        qaMap.put("default", "Xin lỗi, tôi không có thông tin về câu hỏi này. Bạn có thể liên hệ với bộ phận hỗ trợ qua email support@educatrix.com hoặc hotline 1900xxxx để được giúp đỡ.");
    }

    public String getAnswer(String question) {
        String normalizedQuestion = question.toLowerCase().trim();

        for (Map.Entry<String, String> entry : qaMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            if (normalizedQuestion.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

      return qaMap.get("default");
    }

    public void addQA(String keyword, String answer) {
        qaMap.put(keyword.toLowerCase().trim(), answer);
    }
}