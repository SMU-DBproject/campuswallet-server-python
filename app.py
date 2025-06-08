from flask import Flask, request, jsonify
from datetime import datetime
import config
from models import db, Student, Discount, Spending

app = Flask(__name__)

# DB 연결 설정
db_conf = config.DB_CONFIG
app.config['SQLALCHEMY_DATABASE_URI'] = (
    f"mysql+pymysql://{db_conf['user']}:{db_conf['password']}@"
    f"{db_conf['host']}:{db_conf['port']}/{db_conf['database']}"
)
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

# db 초기화(app에 연결)
db.init_app(app)

@app.route('/spending', methods=['POST'])
def create_spending():
    data = request.json

    amount = data.get('amount')
    if amount is None or amount < 0:
        return jsonify({"error": "지출 금액은 0 이상이어야 합니다."}), 400

    student_number = data.get('student_number')
    category = data.get('category')
    discount_id = data.get('discount_id')  # 선택적

    if not student_number or not category:
        return jsonify({"error": "student_number와 category는 필수입니다."}), 400

    spending = Spending(
        student_number=student_number,
        discount_id=discount_id,
        category=category,
        amount=amount,
        date=datetime.now()
    )

    # DB 세션 추가 및 커밋
    with app.app_context():
        db.session.add(spending)
        db.session.commit()

    return jsonify({"message": "지출 내역이 정상 등록되었습니다."}), 201

if __name__ == "__main__":
    app.run(debug=True)
