// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 此处后端没有提供注释 POST /userInterfaceInfo/add */
export async function addUserInterfaceInfo(
  body: API.UserInterfaceInfoAddDto,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityLong>('/userInterfaceInfo/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /userInterfaceInfo/delete */
export async function deleteUserInterfaceInfo(body: API.IdDto, options?: { [key: string]: any }) {
  return request<API.ResponseEntityBoolean>('/userInterfaceInfo/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /userInterfaceInfo/get */
export async function getUserInterfaceInfoById(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserInterfaceInfoByIdParams,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityUserInterfaceInfoDo>('/userInterfaceInfo/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /userInterfaceInfo/list */
export async function listUserInterfaceInfo(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listUserInterfaceInfoParams,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityListUserInterfaceInfoDo>('/userInterfaceInfo/list', {
    method: 'GET',
    params: {
      ...params,
      userInterfaceInfoQueryDto: undefined,
      ...params['userInterfaceInfoQueryDto'],
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /userInterfaceInfo/list/page */
export async function listUserInterfaceInfoByPage(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listUserInterfaceInfoByPageParams,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityPageUserInterfaceInfoDo>('/userInterfaceInfo/list/page', {
    method: 'GET',
    params: {
      ...params,
      userInterfaceInfoQueryPageDto: undefined,
      ...params['userInterfaceInfoQueryPageDto'],
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /userInterfaceInfo/update */
export async function updateUserInterfaceInfo(
  body: API.UserInterfaceInfoUpdateDto,
  options?: { [key: string]: any },
) {
  return request<API.ResponseEntityBoolean>('/userInterfaceInfo/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
