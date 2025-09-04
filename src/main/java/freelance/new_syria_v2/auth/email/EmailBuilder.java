package freelance.new_syria_v2.auth.email;

import java.time.Year;

public class EmailBuilder {

    public static String buildEmail(String name, String link) {
        String template = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
		          * {
		            margin: 0;
		            padding: 0;
		            box-sizing: border-box;
		        }
		        
		        body {
		            font-family: 'Tajawal', sans-serif;
		            background-color: #f5f5f5;
		            margin: 0;
		            padding: 20px;
		            color: #333;
		        }
		        
		        .container {
		            max-width: 600px;
		            margin: 0 auto;
		            background: white;
		            border-radius: 12px;
		            overflow: hidden;
		            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
		        }
		        
		        .header {
		            background: linear-gradient(135deg, #ce1126 0%, #ce1126 33%, #ffffff 33%, #ffffff 66%, #007a3d 66%, #007a3d 100%);
		            padding: 30px 20px;
		            text-align: center;
		            position: relative;
		        }
		        
		        .header::after {
		            content: "";
		            position: absolute;
		            top: 50%;
		            left: 50%;
		            transform: translate(-50%, -50%);
		            width: 60px;
		            height: 60px;
		            background: radial-gradient(circle, #007a3d 0%, #007a3d 40%, transparent 40%),
		                        radial-gradient(circle, transparent 50%, #ffffff 50%, #ffffff 60%, #ce1126 60%);
		            border-radius: 50%;
		        }
		        
		        .header h1 {
		            color: white;
		            font-size: 28px;
		            margin: 0;
		            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
		        }
		        
		        .content {
		            padding: 30px;
		        }
		        
		        .greeting {
		            font-size: 20px;
		            margin-bottom: 20px;
		            color: #222;
		        }
		        
		        .message {
		            font-size: 16px;
		            line-height: 1.6;
		            margin-bottom: 25px;
		            color: #444;
		        }
		        
		        .btn {
		            display: block;
		            width: 80%;
		            margin: 30px auto;
		            padding: 15px;
		            background: linear-gradient(to right, #ce1126, #007a3d);
		            color: white;
		            text-decoration: none;
		            font-weight: bold;
		            font-size: 18px;
		            text-align: center;
		            border-radius: 50px;
		            transition: all 0.3s ease;
		            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
		        }
		        
		        .btn:hover {
		            transform: translateY(-3px);
		            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.3);
		        }
		        
		        .expiry {
		            text-align: center;
		            font-size: 14px;
		            color: #ce1126;
		            margin-bottom: 20px;
		            font-weight: bold;
		        }
		        
		        .footer {
		            background: #f8f8f8;
		            padding: 20px;
		            text-align: center;
		            font-size: 12px;
		            color: #666;
		            border-top: 1px solid #eee;
		        }
		        
		        .logo {
		            font-weight: bold;
		            color: #007a3d;
		            font-size: 16px;
		            margin-top: 10px;
		        }
		        
		        .stars {
		            color: #ce1126;
		            margin: 5px 0;
		        }
		        
		        @media (max-width: 600px) {
		            .header h1 {
		                font-size: 22px;
		            }
		            
		            .content {
		                padding: 20px;
		            }
		            
		            .btn {
		                width: 90%;
		                font-size: 16px;
		            }
		        }
        		</style>
            </head>
    <body>
            <div class="container">
                <h1>Confirm your email</h1>
                <p>Hi {{name}},</p>
                <p>Thank you for registering. Please click the button below to activate your account:</p>
                <a href="{{link}}" class="btn">Activate Now</a>
                <p>This link will expire in <b>15 minutes</b>.</p>
                <p>See you soon!</p>
                <div class="footer">
                    &copy; {{year}} Your Company. All rights reserved.
                </div>
            </div>
        </body>
        </html>
        """;
	return template
        .replace("{{name}}", name)
        .replace("{{link}}", link)
        .replace("{{year}}", String.valueOf(Year.now().getValue()));
}

    public static String buildOtpEmail(String name, String otp) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        font-family: Arial, Helvetica, sans-serif;
                        background-color: #f4f4f4;
                        margin: 0;
                        padding: 0;
                    }
                    .container {
                        max-width: 580px;
                        margin: auto;
                        background: #ffffff;
                        padding: 20px;
                        border-radius: 8px;
                        box-shadow: 0 0 10px rgba(0,0,0,0.1);
                    }
                    h1 {
                        color: #0b0c0c;
                        font-size: 22px;
                    }
                    p {
                        color: #0b0c0c;
                        font-size: 16px;
                        line-height: 1.5;
                    }
                    .otp {
                        display: inline-block;
                        font-size: 24px;
                        font-weight: bold;
                        letter-spacing: 4px;
                        padding: 10px 20px;
                        background-color: #f0f0f0;
                        border-radius: 5px;
                        margin: 20px 0;
                        color: #1D70B8;
                    }
                    .footer {
                        margin-top: 20px;
                        font-size: 12px;
                        color: #888888;
                        text-align: center;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>Your OTP Code</h1>
                    <p>Hi %s,</p>
                    <p>Here is your OTP code for password reset:</p>
                    <div class="otp">%s</div>
                    <p>This OTP will expire in <b>15 minutes</b>.</p>
                    <p>If you did not request this, please ignore this email.</p>
                    <div class="footer">
                        &copy; %d Your Company. All rights reserved.
                    </div>
                </div>
            </body>
            </html>
        """.formatted(name, otp, java.time.Year.now().getValue());
    }
}
