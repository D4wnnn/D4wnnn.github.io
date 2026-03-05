package com.acacm;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.v3.codec.ContractCodecException;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderService;

import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String configFile = "C:\\Users\\28185\\Desktop\\ACACM\\ACACM2\\src\\main\\resources\\config-example.toml";
        BcosSDK sdk = BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");
        // 获取群组1的块高
        BlockNumber blockNumber = client.getBlockNumber();
        System.out.println(blockNumber.getBlockNumber());
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();

        // rInfo
        Utf8String rID = new Utf8String("U0002");
        Utf8String rType = new Utf8String("ECG-monitor");
        Utf8String rName = new Utf8String("ECG-monitor-1");
        Utf8String rIP = new Utf8String("192.168.126.165");
        Utf8String rPort = new Utf8String("10000");
        Utf8String rSensitiveLevel = new Utf8String("medium");
        Utf8String rLocation = new Utf8String("hospital1");
        Utf8String rStatus = new Utf8String("active");
        DynamicArray<Utf8String> rPermissions = new DynamicArray<>(Utf8String.class, new Utf8String("read"), new Utf8String("write"), new Utf8String("exec"), new Utf8String("close"));
        DynamicArray<Utf8String> rBasicRules = new DynamicArray<>(Utf8String.class, new Utf8String("open"));
        DynamicArray<Utf8String> rPrivateAccess = new DynamicArray<>(Utf8String.class, new Utf8String("exec"));
        SmartLink.RInfo rInfo = new SmartLink.RInfo(rID, rType, rName, rIP, rPort, rSensitiveLevel, rLocation, rStatus, rPermissions, rBasicRules, rPrivateAccess);
        //User
        Utf8String uID = new Utf8String("U0001");
        Utf8String uName = new Utf8String("Alice");
        Utf8String uPassword = new Utf8String("123");
        Utf8String uAttr = new Utf8String("");
        Uint256 uPK = new Uint256(new BigInteger("510098207738138866919894821648179017632090164778"));
        SmartLink.UInfo uInfo = new SmartLink.UInfo(uID, uName, uPassword, uAttr, uPK);
        //UpdateInfo
        Boolean abacMod = true;
        BigInteger subject = new BigInteger("0");
        String attributes = "";
        List<String> operations = new ArrayList<>();
        SmartLink.UpdateInfo updateInfo = new SmartLink.UpdateInfo(abacMod, subject, attributes, operations);
        //UpdateTL
        BigInteger subjectTL = new BigInteger("0");
        BigInteger value = new BigInteger("0");
        SmartLink.UpdateTL updateTL = new SmartLink.UpdateTL(subjectTL, value);
        //cList
        List<String> cList = new ArrayList<>();
        SmartLink sc = new SmartLink("37a44585bf1e9618fdb4c62c4c96189a07dd4b48", client, cryptoKeyPair);
        CryptoSuite cryptoSuite = client.getCryptoSuite();
        TransactionDecoderInterface decoder = new TransactionDecoderService(cryptoSuite.getHashImpl(), client.isWASM());
        String abi = "[{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_sa\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"_sc\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"_temc\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"_factoryAdd\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"_result\",\"type\":\"bool\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"_msg\",\"type\":\"string\"},{\"components\":[{\"internalType\":\"bool\",\"name\":\"isvalid\",\"type\":\"bool\"},{\"components\":[{\"internalType\":\"string\",\"name\":\"rName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rIP\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rPort\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rSensitiveLevel\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rLocation\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rStatus\",\"type\":\"string\"},{\"internalType\":\"string[]\",\"name\":\"rPrivateAccess\",\"type\":\"string[]\"}],\"internalType\":\"struct TokenRInfo\",\"name\":\"tokenRInfo\",\"type\":\"tuple\"},{\"components\":[{\"internalType\":\"string\",\"name\":\"uName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"uPK\",\"type\":\"uint256\"}],\"internalType\":\"struct TokenUInfo\",\"name\":\"tokenUInfo\",\"type\":\"tuple\"},{\"internalType\":\"string[]\",\"name\":\"rights\",\"type\":\"string[]\"},{\"internalType\":\"uint256\",\"name\":\"tl\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"sig\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"expireTime\",\"type\":\"uint256\"}],\"indexed\":false,\"internalType\":\"struct Token\",\"name\":\"_token\",\"type\":\"tuple\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_timestamp\",\"type\":\"uint256\"}],\"name\":\"SystemResponse\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"string\",\"name\":\"result\",\"type\":\"string\"}],\"name\":\"authLog\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"components\":[{\"internalType\":\"bool\",\"name\":\"isvalid\",\"type\":\"bool\"},{\"components\":[{\"internalType\":\"string\",\"name\":\"rName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rIP\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rPort\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rSensitiveLevel\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rLocation\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rStatus\",\"type\":\"string\"},{\"internalType\":\"string[]\",\"name\":\"rPrivateAccess\",\"type\":\"string[]\"}],\"internalType\":\"struct TokenRInfo\",\"name\":\"tokenRInfo\",\"type\":\"tuple\"},{\"components\":[{\"internalType\":\"string\",\"name\":\"uName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"uPK\",\"type\":\"uint256\"}],\"internalType\":\"struct TokenUInfo\",\"name\":\"tokenUInfo\",\"type\":\"tuple\"},{\"internalType\":\"string[]\",\"name\":\"rights\",\"type\":\"string[]\"},{\"internalType\":\"uint256\",\"name\":\"tl\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"sig\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"expireTime\",\"type\":\"uint256\"}],\"indexed\":false,\"internalType\":\"struct Token\",\"name\":\"token\",\"type\":\"tuple\"}],\"name\":\"genToken\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"string\",\"name\":\"result\",\"type\":\"string\"}],\"name\":\"rigisterLog\",\"type\":\"event\"},{\"inputs\":[{\"components\":[{\"internalType\":\"string\",\"name\":\"et\",\"type\":\"string\"},{\"components\":[{\"internalType\":\"string\",\"name\":\"rID\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rType\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rIP\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rPort\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rSensitiveLevel\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rLocation\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"rStatus\",\"type\":\"string\"},{\"internalType\":\"string[]\",\"name\":\"rPermissions\",\"type\":\"string[]\"},{\"internalType\":\"string[]\",\"name\":\"rBasicRules\",\"type\":\"string[]\"},{\"internalType\":\"string[]\",\"name\":\"rPrivateAccess\",\"type\":\"string[]\"}],\"internalType\":\"struct RInfo\",\"name\":\"rInfo\",\"type\":\"tuple\"},{\"components\":[{\"internalType\":\"string\",\"name\":\"uID\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"uName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"uPassword\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"uAttr\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"uPK\",\"type\":\"uint256\"}],\"internalType\":\"struct UInfo\",\"name\":\"uInfo\",\"type\":\"tuple\"},{\"components\":[{\"internalType\":\"bool\",\"name\":\"abacMod\",\"type\":\"bool\"},{\"internalType\":\"uint256\",\"name\":\"subject\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"attributes\",\"type\":\"string\"},{\"internalType\":\"string[]\",\"name\":\"operations\",\"type\":\"string[]\"}],\"internalType\":\"struct UpdateInfo\",\"name\":\"updateInfo\",\"type\":\"tuple\"},{\"components\":[{\"internalType\":\"uint256\",\"name\":\"subject\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"internalType\":\"struct UpdateTL\",\"name\":\"updateTL\",\"type\":\"tuple\"},{\"internalType\":\"string[]\",\"name\":\"cList\",\"type\":\"string[]\"}],\"internalType\":\"struct ReqData\",\"name\":\"_reqData\",\"type\":\"tuple\"}],\"name\":\"dispatchRequest\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_uPK\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"_rName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_sig\",\"type\":\"uint256\"}],\"name\":\"sig_verify\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]";
        SmartLink.ReqData reqData = new SmartLink.ReqData("registerNewDevice", rInfo, uInfo, updateInfo, updateTL, cList);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            new Thread(new MyThread(startTime, sc, reqData, decoder, abi)).start();
        }
    }
}

class MyThread implements Runnable {
    private final SmartLink sc;
    private final SmartLink.ReqData reqData;
    private final TransactionDecoderInterface decoder;
    private final String abi;
    private final long startTime;

    public MyThread(long startTime, SmartLink sc, SmartLink.ReqData reqData, TransactionDecoderInterface decoder, String abi) {
        this.sc = sc;
        this.reqData = reqData;
        this.decoder = decoder;
        this.abi = abi;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        TransactionReceipt transactionReceipt = sc.dispatchRequest(reqData);
        Map<String, List<List<Object>>> events;
        try {
            events = decoder.decodeEvents(abi, transactionReceipt.getLogEntries());
        } catch (ContractCodecException e) {
            throw new RuntimeException(e);
        }
        System.out.println(events);
//        System.out.println((System.nanoTime() - startTime) / 1000000);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}