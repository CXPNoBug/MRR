package com.cxp.mrr.mvp.model;

/**
 * 文 件 名: LoginModel
 * 创 建 人: CXP
 * 创建日期: 2017-12-04 16:30
 * 描    述: 登录实体
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class LoginModel {

    private String Res_Code;
    private String Res_Message;
    private String gestureInfo;
    private String identity;
    private Member member;

    public String getRes_Code() {
        return Res_Code;
    }

    public void setRes_Code(String res_Code) {
        Res_Code = res_Code;
    }

    public String getRes_Message() {
        return Res_Message;
    }

    public void setRes_Message(String res_Message) {
        Res_Message = res_Message;
    }

    public String getGestureInfo() {
        return gestureInfo;
    }

    public void setGestureInfo(String gestureInfo) {
        this.gestureInfo = gestureInfo;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

   public class Member {

        private String bANK_BRANCH;
        private String bANK_LINKED;
        private String bANK_NAME;
        private int iSRISK_MANAGEMENT;
        private int iS_STORE;
        private String mEMBER_BANK_CARD;
        private String mEMBER_CERTNO;
        private int mEMBER_COMEFROM;

        public String getbANK_BRANCH() {
            return bANK_BRANCH;
        }

        public void setbANK_BRANCH(String bANK_BRANCH) {
            this.bANK_BRANCH = bANK_BRANCH;
        }

        public String getbANK_LINKED() {
            return bANK_LINKED;
        }

        public void setbANK_LINKED(String bANK_LINKED) {
            this.bANK_LINKED = bANK_LINKED;
        }

        public String getbANK_NAME() {
            return bANK_NAME;
        }

        public void setbANK_NAME(String bANK_NAME) {
            this.bANK_NAME = bANK_NAME;
        }

        public int getiSRISK_MANAGEMENT() {
            return iSRISK_MANAGEMENT;
        }

        public void setiSRISK_MANAGEMENT(int iSRISK_MANAGEMENT) {
            this.iSRISK_MANAGEMENT = iSRISK_MANAGEMENT;
        }

        public int getiS_STORE() {
            return iS_STORE;
        }

        public void setiS_STORE(int iS_STORE) {
            this.iS_STORE = iS_STORE;
        }

        public String getmEMBER_BANK_CARD() {
            return mEMBER_BANK_CARD;
        }

        public void setmEMBER_BANK_CARD(String mEMBER_BANK_CARD) {
            this.mEMBER_BANK_CARD = mEMBER_BANK_CARD;
        }

        public String getmEMBER_CERTNO() {
            return mEMBER_CERTNO;
        }

        public void setmEMBER_CERTNO(String mEMBER_CERTNO) {
            this.mEMBER_CERTNO = mEMBER_CERTNO;
        }

        public int getmEMBER_COMEFROM() {
            return mEMBER_COMEFROM;
        }

        public void setmEMBER_COMEFROM(int mEMBER_COMEFROM) {
            this.mEMBER_COMEFROM = mEMBER_COMEFROM;
        }
    }
}
