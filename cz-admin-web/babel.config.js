const plugins = [
  ['prismjs', {
    'languages': ['javascript', 'css', 'markup', 'python', 'java'],
    'theme': 'coy',
    'css': true
  }],
  '@vue/babel-plugin-transform-vue-jsx'
]
// 生产环境移除console
if (process.env.NODE_ENV === 'production') {
  plugins.push('transform-remove-console')
} else {
  plugins.push('dynamic-import-node')
}
module.exports = {
  plugins: plugins,
  presets: [
    '@vue/app'
  ]
}
