import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'

// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcID4uccUVmDg3UQ7tYLNGEByoGejS6jMGOWPEASqm2eBKjA8zo5D4sl06dRXZOnnbcJnSmO3cNv57sDfrzFp3mpDLHkZxFMs7akIWhNM7IGb7Psbtk4ceXtFPPShXF+ohO90cOHV51PbrPsAOgn9cVjA0HrKnG1U9xJzDBMzj/QIDAQAB'

// 加密
export function encrypt (txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对需要加密的数据进行加密
}

