import defaultSettings from '@/settings'
import i18n from '@/lang'
export default function getPageTitle (pageTitle) {
  const title = i18n.t(defaultSettings.title)
  if (pageTitle) {
    if (i18n.te('route.' + pageTitle)) {
      return `${i18n.t('route.' + pageTitle)} - ${title.replace(/\s/g, '')}`
    }
    return `${pageTitle} - ${title.replace(/\s/g, '')}`
  }
  return `${title}`
}
